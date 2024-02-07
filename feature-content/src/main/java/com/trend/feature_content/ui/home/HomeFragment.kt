package com.trend.feature_content.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.trend.chevron.databinding.FragmentHomeBinding
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import com.trend.feature_common.models.ParadasModel
import com.trend.feature_common.models.StopContinueModel
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_common.utils.DetectTypeAccount
import com.trend.feature_common.utils.DetectTypeAccount.Companion.getNameTypeAccount
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.cellPhone
import com.trend.feature_common.utils.PreferencesHelper.idEnterprise
import com.trend.feature_common.utils.PreferencesHelper.idUser
import com.trend.feature_common.utils.PreferencesHelper.isLogged
import com.trend.feature_common.utils.PreferencesHelper.nameUser
import com.trend.feature_common.utils.PreferencesHelper.timeToStudy
import com.trend.feature_common.utils.PreferencesHelper.typeAccount
import com.trend.feature_content.ui.information.FinishLessonActivity
import com.trend.feature_content.ui.information.MainShowContentActivity
import com.trend.feature_tests.ui.MainExamenActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ApplyUIMainContent by ApplyUIMainContentImpl() {

    private lateinit var binding: FragmentHomeBinding
    private var typeAccount = TypeAccount.HAVOLINE
    private var _CELLPHONE_: String = ""
    private var _ID_ENTERPRISE_: Int = 0
    private var _NAME_USER_ : String = ""
    private var _STUDY_TIME_ : Int = 0
    private var _ID_USER_: Int = 0

    private lateinit var adapter: MainRoutesAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var prefs: SharedPreferences
    private var numRoute = 1
    private var stay: StopContinueModel? = null
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        prefs = PreferencesHelper.customPreference(requireActivity(), constants.CUSTOM_PREF_NAME)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initData()
        applyUI()
        setupWelcomeMessage()
        setupObservers()
        setupListeners()
        return binding.root
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.maincontent.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> { ProgressUtil.showLoading(requireActivity()) }
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        setupAdapter(it.data)
                    }
                    is BaseEvent.Error -> {
                        ProgressUtil.hideLoading()
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupAdapter(content: MainContentResponse) {
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        binding.mainContentRoutes.layoutManager = linearLayout
        binding.mainContentRoutes.setHasFixedSize(true)

        stay = detectNextStop(content)
        numRoute = stay?.ruta?.filter { route -> route in "0123456789" }?.toInt() ?: 1

        applyUIShieldNumber(
            context = requireActivity(),
            route = numRoute,
            typeAccount = typeAccount,
            binding = binding
        )

        binding.labelTitleStay.text = stay?.parada?.uppercase()

        adapter = MainRoutesAdapter(requireContext(), OnClickListener { parada, valor, idStop, statusLesson, IdRoute ->
            startActivity(Intent(requireContext(), MainShowContentActivity::class.java).apply {
                putExtra(constants.VALUE_DATA, valor)
                putExtra(constants.BODY1, parada.ch_Text1)
                putExtra(constants.BODY2, parada.ch_Text2)
                putExtra(constants.PATH_AUDIO, parada.ch_AudioPath)
                putExtra(constants.PATH_IMAGE, parada.ch_AudioImage)
                putExtra(constants.TITLE, parada.ch_Title3)
                putExtra(constants.PARADA, parada.ch_NameStop)
                putExtra(constants.IDSTOP, idStop)
                putExtra(constants.IDUSER, _ID_USER_)
                putExtra(constants.STATUSLESSON, statusLesson)
                putExtra(constants.TYPE_ACCOUNT, typeAccount)
                putExtra(constants.IDROUTE, IdRoute)
            })
        }, typeAccount,
            OnClickOpenRouteTest { IdRoute, IdTest, IdStop ->
                if(IdTest > 0) {
                    val mIntent = Intent(activity, MainExamenActivity::class.java).apply {
                        putExtra(constants.IDROUTE, IdRoute)
                        putExtra(constants.IDSTOP, IdStop)
                        putExtra(constants.IDTEST, IdTest)
                        putExtra(constants.TYPE_ACCOUNT, typeAccount)
                    }
                    activity?.startActivity(mIntent)
                } else {
                    Snackbar.make(binding.root, "No existe un test asignado", Snackbar.LENGTH_LONG).show()
                }
            }
        )
        adapter.setData(content.routes)
        binding.mainContentRoutes.adapter = adapter
    }

    private fun detectNextStop(content: MainContentResponse): StopContinueModel? {
        run detectStops@ {
            content.routes.forEach{ routes ->
                counter++
                routes.ch_Paradas.forEach { stops ->
                    if(stops.ch_IdStatus == "A") {
                        return StopContinueModel(routes.ch_NameRoute, stops.ch_NameStop)
                    }
                }
            }
        }
        return null
    }

    private fun applyUI() {
        applyMainContent(
            typeAccount = typeAccount,
            binding = binding
        )
    }

    fun initData() {
        if(prefs.isLogged) {
            typeAccount = DetectTypeAccount.getTypeAccount(prefs.typeAccount.toString())
            _CELLPHONE_ = prefs.cellPhone.toString()
            _ID_ENTERPRISE_ = prefs.idEnterprise
            _ID_USER_ = prefs.idUser
            _NAME_USER_ = prefs.nameUser.toString()
            _STUDY_TIME_ = prefs.timeToStudy
        } else {
            arguments?.let {
                typeAccount = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
                _CELLPHONE_ = it.getString(constants.CELLPHONE).toString()
                _ID_ENTERPRISE_ = it.getInt(constants.ID_ENTERPRISE)
                _ID_USER_ = it.getInt(constants.ID_USER)
                _NAME_USER_ = it.getString(constants.NAMEUSER).toString()
                _STUDY_TIME_ = it.getInt(constants.STUDYTIME)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setupWelcomeMessage(){
        binding.textWelcomeMessage.text = "Bienvenido\nRétate tomando las\nlecciones y supéralas para\nser un experto ${getNameTypeAccount(typeAccount)}"
    }

    private fun openEmailToSend(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupListeners() {
        binding.textHelp.setOnClickListener {
            val recipient = "servicioalcliente@expertochevron.com"
            val subject = "Se requiere apoyo"
            val message = ""

            openEmailToSend(recipient, subject, message)
        }
    }

    class OnClickListener(val clickListener: (parada: ParadasModel, valor: Int, idStop: Int, statusLesson: Int, IdRoute: Int) -> Unit) {
        fun onClick(parada: ParadasModel, valor: Int, idStop: Int, statusLesson: Int, IdRoute: Int) = clickListener(parada, valor, idStop, statusLesson, IdRoute)
    }

    class OnClickOpenRouteTest(val openTest: (IdRoute: Int, IdTest: Int, IdStop: Int) -> Unit) {
        fun onClick(IdRoute: Int, IdTest: Int, IdStop: Int) = openTest(IdRoute, IdTest, IdStop)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMainContent(MainContentRequest(prefs.idUser, prefs.idEnterprise))
    }
}

