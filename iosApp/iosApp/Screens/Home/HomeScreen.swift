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
    
    var body: some View {
        VStack {
            HomeScreenContent(state: state,onRefresh: {viewModel.getWeather()})
        }.toolbar {
            NavigationLink(destination: SettingsScreen()){
                Label("Settings", systemImage: "gear")
            }
        }.onAppear {
            viewModel.getWeather()
        }
    }
}


struct HomeScreenContent : View {
    private  let refreshControlKey = "RefreshControl"
    
    let state : ViewState<Weather>
    let onRefresh : () -> Void
    
    var body: some View {
        ScrollView {
            RefreshControl(coordinateSpace: .named(refreshControlKey)) {
                onRefresh()
            }
            ViewStateHandler(state: state) { data in
                VStack(alignment: .center) {
                    MainWeatherWidget(weather: data)
                    
                    HStack {
                        Card(label: "Pressure", value: String(format: "%.2f", data.airPressure), units: "milibars")
                        Card(label: "Humidity", value: String(format: "%.2f", data.humidityInPercents), units: "%")
                    }
                    HStack {
                        Card(label: "Visibilit", value: String(format: "%.2f", data.visibilityInMiles), units: "miles")
                        Card(label: "Wind", value: String(format: "%.2f", data.windSpeedInMpH), units: "mph")
                    }
                }.padding()
                
            } error: { errorMessage in
                Text("Error: \(errorMessage)")
            } loading: {
                ProgressView()
            }
            
        }.coordinateSpace(name: refreshControlKey)
    }
}
