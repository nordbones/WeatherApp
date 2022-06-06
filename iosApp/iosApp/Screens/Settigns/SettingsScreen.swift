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

struct SettingsScreen: View {
    
    @ObservedObject var viewModel = SettingsViewModel()
    
    private var state: ViewState<Locations> {
        get {
            return viewModel.state
        }
    }
    
    var body: some View{
        SettingScreenContent(state: state, location: $viewModel.location, onClearLocation: {
            viewModel.onLocationClear()
        }, onSaveLocation: { location in
            viewModel.saveLocation(location: location)
        })
        .onAppear(){
            viewModel.getLocation()
        }
        .frame(width: .infinity, height: .infinity)
        .navigationTitle("Settings")
    }
}


struct SettingScreenContent: View {
    
    let state: ViewState<Locations>
    var location:Binding<String>
    let onClearLocation:()->Void
    let onSaveLocation:(Location)->Void
    
    
    var body: some View{
        VStack {
            HStack {
                TextField("Location",text: location)
                Image(systemName: "clear").onTapGesture {
                    onClearLocation()
                }
            }
            .padding()
            .overlay(RoundedRectangle(cornerRadius: 10)
                .stroke(lineWidth: 2).foregroundColor(Color.black))
            .padding()
            
            ViewStateHandler(state: state) { locations in
                List(locations.list) { location in
                    Button(location.getFullTitle()) {
                        onSaveLocation(location)
                    }
                }
            } error: { errorMessage in
                DefaultErrorView(errorMessage: errorMessage)
            } loading: {
                DefaultLoadingView()
            }
            Spacer()
        }
    }
}

extension Location : Identifiable {
    
}
