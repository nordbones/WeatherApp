//
//  HomeScreen.swift
//  iosApp
//
//  Created by Никита on 27.05.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared


struct HomeScreen: View {
    
    @ObservedObject var viewModel = HomeViewModel()
    
    private var state: ViewState<Weather> {
        get{
            return viewModel.state
        }
    }
    
    var body: some View{
        VStack{
           HomeScreenContent(state: state)
        }.toolbar {
            NavigationLink(destination: SettingsScreen()){
                Label("Settings", systemImage: "gear")
            }
        }
    }
}


struct HomeScreenContent : View {
    let state : ViewState<Weather>
    
    var body: some View {
        Text("Content")
    }
}
