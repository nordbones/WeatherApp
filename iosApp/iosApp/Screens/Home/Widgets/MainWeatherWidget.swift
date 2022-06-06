//
//  MainWeatherWidget.swift
//  iosApp
//
//  Created by Никита on 2.06.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct MainWeatherWidget : View {
    let weather:Weather
    
    var body: some View {
        VStack {
            Text("\(weather.name) \(weather.country)")
                .font(.system(size: 32))
                .foregroundColor(Color.white)
            
            Text(weather.description_)
                .font(.system(size: 24))
                .foregroundColor(Color.white)
            
            Text("\(weather.temp)")
                .font(.system(size: 60))
                .foregroundColor(Color.white)
            
        }
        .frame(minWidth: 0,maxWidth: .infinity, alignment: .center)
        .padding()
        .background(Color.blue)
        .clipShape(RoundedRectangle(cornerRadius: 10))
    }
}

