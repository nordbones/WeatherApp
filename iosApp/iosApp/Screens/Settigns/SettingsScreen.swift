//
//  SettingsScreen.swift
//  iosApp
//
//  Created by Никита on 27.05.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SettingsScreen: View{
    
    @ObservedObject var viewModel = SettingsViewModel()
    @State private var location: String = String.EMPTY
    
    private var state: ViewState<Locations> {
        get{
            return viewModel.state
        }
    }
    
    var body: some View{
//         VStack{
//             HStack{
//             TextField(
//                     "Location",
//                     text: $location
//                 )
//                 Button("Search") {
//                     viewModel.searchLocation(value: location)
//                 }
//             }
//
//         }.navigationTitle("Settings")
        SettingScreenContent(state: state).navigationTitle("Settings")
    }
}


struct SettingScreenContent: View {
    
    let state: ViewState<Locations>
    
    var body: some View{
        Text("Content")
        
      
    }
}


