package com.project.tavernskeep.screens.mesas.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.project.tavernskeep.models.MesasModel
import com.project.tavernskeep.viewsModels.MesasViewModel

@Composable
fun MesasList (navController: NavController, mesasModel: MesasViewModel) {
    LazyColumn {
        itemsIndexed(items = mesasModel.mesasModelListResponse.sortedBy {M -> M.codigo}.toList()) {
                _, item -> MesasItem(navController = navController, mesasModel = item, mesas = mesasModel)
        }
    }
}