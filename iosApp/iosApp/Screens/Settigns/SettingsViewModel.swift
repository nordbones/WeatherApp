//
//  SettingsViewModel.swift
//  iosApp
//
//  Created by Никита on 27.05.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class SettingsViewModel:ObservableObject{
    private let sharedViewModel = appModule.locationSharedViewModel
    
    @Published var state = ViewState<Locations>(isLoading: false, data: nil, error: nil)
    
    func searchLocation(value:String){
        sharedViewModel.searchLocation(location: value).collectCommon { result in
            self.state = result!
        }
    }
    
    func saveLocation(location:Location){
        sharedViewModel.saveLocation(location: location) { _, _ in
        }
    }
}
