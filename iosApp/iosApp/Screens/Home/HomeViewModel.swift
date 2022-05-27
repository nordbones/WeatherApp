//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Никита on 27.05.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class HomeViewModel :ObservableObject{
    private let sharedViewModel = appModule.weatherSharedViewModel
    
    @Published var state = ViewState<Weather>(isLoading: false, data:nil, error:nil)
    
    func getWeather(){
        sharedViewModel.getWeather().collectCommon { [self] newState in
            self.state = newState!
        }
    }
}
