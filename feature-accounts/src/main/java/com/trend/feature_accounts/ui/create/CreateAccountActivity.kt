package com.trend.feature_accounts.ui.create

import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.trend.chevron.databinding.ActivityCreateAccountBinding
import com.trend.feature_common.adapters.CitiesSpinnerAdapter
import com.trend.feature_common.adapters.CountriesCellphoneDataAdapter
import com.trend.feature_common.adapters.CountriesSpinnerAdapter
import com.trend.feature_common.adapters.ServiceStationAdapter
import com.trend.feature_common.delegates.ApplyLoadLocalDropdown
import com.trend.feature_common.delegates.ApplyLoadLocalDropdownImpl
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.extensiones.openActivity
import com.trend.feature_common.models.CitiesModel
import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CountriesCellphoneDataModel
import com.trend.feature_common.models.CountriesModel
import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.MonthsModel
import com.trend.feature_common.models.SSModel
import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.StudyTimeModel
import com.trend.feature_common.models.UsersModel
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_common.utils.AlertDialogHelper
import com.trend.feature_common.utils.CountriesDataHelper
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.birthday
import com.trend.feature_common.utils.PreferencesHelper.countryCode
import com.trend.feature_common.utils.PreferencesHelper.idCountry
import com.trend.feature_common.utils.PreferencesHelper.idEnterprise
import com.trend.feature_common.utils.PreferencesHelper.idUser
import com.trend.feature_common.utils.PreferencesHelper.timeToStudy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateAccountActivity : AppCompatActivity(), OnClickListener,
    ApplyUiTypeAccount by ApplyUiTypeAccountImpl(),
    ApplyLoadLocalDropdown by ApplyLoadLocalDropdownImpl(),
    ApplyUIWhenIsEditing by ApplyUIWhenIsEditingImpl(),
    ApplyValidateForm by ApplyValidateFormImpl() {

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var typeAccount: TypeAccount
    private val viewModel: CreateAccountViewModel by viewModels()
    private lateinit var prefs: SharedPreferences
    private lateinit var adapterCountryPhone: CountriesCellphoneDataAdapter
    private var isEditMode: Boolean = false
    private var isEditable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        prefs = PreferencesHelper.customPreference(this, constants.CUSTOM_PREF_NAME)
        adapterCountryPhone = CountriesCellphoneDataAdapter(this, CountriesDataHelper.getCountriesData())
        setContentView(binding.root)
        catchArguments()
        setupListener()
        setUiTypeAccount()
        setUiWhenIsEdit()
        setupObservers()
        loadLocalDropdown()
        setupData()
    }

    fun catchArguments() {
        typeAccount = intent.extras?.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
        isEditMode = intent.extras?.getBoolean(constants.ISEDIT) as Boolean
        isEditable = isEditMode
    }

    private fun setupData() {
        prefs.birthday?.let { binding.spnBirthday.setSelection(it.toInt()) }

        prefs.countryCode?.let {
            val countryCode = CountriesDataHelper.getCountriesData().find { it.code == prefs.countryCode }
            val position = adapterCountryPhone.getPosition(countryCode)
            binding.spnCountryPhone.setSelection(position)
        }

        binding.spnStudyTime.setSelection(when(prefs.timeToStudy) {
            5 -> 0
            10 -> 1
            15 -> 2
            else -> 0
        })
        if(!isEditMode) binding.btnDelete.visibility = View.GONE
    }

    private fun loadLocalDropdown() {
        applyLoadDropDown(
            context = this,
            spnCountryPhone = binding.spnCountry,
            spnBirthday = binding.spnBirthday,
            spnStudyTime = binding.spnStudyTime,
            countryPhone = adapterCountryPhone
        )
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.countries.collect {
                        when(it) {
                            is BaseEvent.Init -> {}
                            is BaseEvent.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is BaseEvent.Success -> {
                                binding.progressBar.visibility = View.GONE
                                it.data.countries.add(CountriesModel(0, "-- Selecciona tu país --", ""))
                                val adapter = CountriesSpinnerAdapter(this@CreateAccountActivity, it.data.countries, false)
                                binding.spnCountry.adapter = adapter
                                val pais = it.data.countries.find { it.ch_Id == prefs.idCountry}
                                val position = adapter.getPosition(pais)
                                binding.spnCountry.setSelection(position)
                            }
                            is BaseEvent.Error -> {
                                Toast.makeText(this@CreateAccountActivity, it.message, Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                            }

                        }
                    }
                }

                launch {
                    viewModel.cities.collect {
                        when(it) {
                            is BaseEvent.Init -> {}
                            is BaseEvent.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is BaseEvent.Success -> {
                                binding.progressBar.visibility = View.GONE
                                it.data.cities.add(CitiesModel(0, 0, "-- Selecciona tu ciudad --"))
                                val adapter = CitiesSpinnerAdapter(this@CreateAccountActivity, it.data.cities, false)
                                binding.spnCity.adapter = adapter
                            }
                            is BaseEvent.Error -> {
                                Toast.makeText(this@CreateAccountActivity, it.message, Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }

                launch {
                    viewModel.ss.collect {
                        when(it) {
                            is BaseEvent.Init -> {}
                            is BaseEvent.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is BaseEvent.Success -> {
                                binding.progressBar.visibility = View.GONE
                                it.data.sss.add(SSModel(0, "-- Selecciona tu estación --"))
                                val adapter = ServiceStationAdapter(this@CreateAccountActivity, it.data.sss)
                                binding.spnSs.adapter = adapter
                            }
                            is BaseEvent.Error -> {
                                Toast.makeText(this@CreateAccountActivity, it.message, Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }

                launch {
                    viewModel.createAccount.collect {
                        when(it) {
                            is BaseEvent.Init -> {}
                            is BaseEvent.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is BaseEvent.Success -> {
                                this@CreateAccountActivity.openActivity(SuccessCreateAccountActivity::class.java, true)
                            }
                            is BaseEvent.Error -> {
                                Toast.makeText(this@CreateAccountActivity, it.message, Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }

                launch {
                    viewModel.updateAccount.collect {
                        when(it) {
                            is BaseEvent.Init -> {}
                            is BaseEvent.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is BaseEvent.Success -> Toast.makeText(this@CreateAccountActivity, it.data.message, Toast.LENGTH_LONG).show()
                            is BaseEvent.Error -> {
                                Toast.makeText(this@CreateAccountActivity, it.message, Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }

                launch {
                    viewModel.deleteAccount.collect {
                        when(it) {
                            is BaseEvent.Init -> {}
                            is BaseEvent.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is BaseEvent.Success -> Toast.makeText(this@CreateAccountActivity, it.data.message, Toast.LENGTH_LONG).show()
                            is BaseEvent.Error -> {
                                Toast.makeText(this@CreateAccountActivity, it.message, Toast.LENGTH_LONG).show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }

            }
        }
        viewModel.getCountries()
    }

    private fun setupListener() {
        binding.btnSave.setOnClickListener(this)

        binding.spnCountry.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                try {
                    val requestCity = CitiesRequest((binding.spnCountry.selectedItem as CountriesModel).ch_Id)
                    val requestSS = SSRequest((binding.spnCountry.selectedItem as CountriesModel).ch_Id)
                    viewModel.getCities(requestCity)
                    viewModel.getServiceStations(requestSS)
                } catch(ex: Exception) {}
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.btnDelete.setOnClickListener { view ->
            AlertDialogHelper.basic(
                this,
                "Preacución",
                "Al eliminar su perfil perderá todo su progreso en la app ExpertoChevron, desea continuar?",
                "SI",
                "NO",
                { dialog: DialogInterface, which: Int ->
                    viewModel.deleteAccount(DeleteProfile(prefs.idUser, prefs.idEnterprise))
                },
                { dialog: DialogInterface, which: Int -> finish() },
                false
            )
        }

        binding.btnSave.setOnClickListener {
            if (validateForm(binding, typeAccount)) {
                val user = UsersModel(
                    ch_Name = binding.textName.text.toString(),
                    ch_LastName = binding.textLastname.text.toString(),
                    ch_Birthday = (binding.spnBirthday.selectedItem as MonthsModel).id,
                    ch_CountryCode = (binding.spnCountryPhone.selectedItem as CountriesCellphoneDataModel).code,
                    ch_Cellphone = binding.textPersonalPhone.text.toString(),
                    ch_CreatedAt = "",
                    ch_Email = binding.textEmail.text.toString(),
                    ch_IdCity = (binding.spnCity.selectedItem as CitiesModel).ch_Id,
                    ch_IdCountry = (binding.spnCountry.selectedItem as CountriesModel).ch_Id,
                    ch_IdEnterprise = typeAccount.ordinal + 1,
                    ch_IdPos = 1,
                    ch_IdServiceStation = (binding.spnSs.selectedItem as SSModel).ch_Id,
                    ch_ServiceStation = binding.textPOS.text.toString(),
                    ch_IdUser = 1,
                    ch_StudyTime = (binding.spnStudyTime.selectedItem as StudyTimeModel).Id
                )
                if(!isEditMode) {
                    viewModel.createAccount(user)
                } else {
                    viewModel.updateAccount(user)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        this.openActivity(SuccessCreateAccountActivity::class.java)
    }

    private fun setUiTypeAccount() {
        applyUiTypeAccount(
            binding,
            typeAccount
        )
    }

    private fun setUiWhenIsEdit() {
        applyUiWhenIdEdit(
            this,
            isEditMode,
            binding,
            prefs,
            typeAccount
        )
    }


}