//
//  SettingsViewModel.swift
//  iosApp
//
//  Created by Никита on 27.05.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class SettingsViewModel:ObservableObject {
    private let sharedViewModel = appModule.locationSharedViewModel
    
    @Published var state = ViewState<Locations>(isLoading: false, data: nil, error: nil)
    @Published var location = String.EMPTY
    {
        didSet {
        if location.count > 5 {
            searchLocation(value: location)
        } else {
            self.state = ViewState<Locations>(isLoading: false, data: nil, error: nil)
        }
        }
    }
    
    func searchLocation(value:String) {
        sharedViewModel.searchLocation(location: value).collectCommon { result in
            self.state = result!
        }
    }
    
    func onLocationClear() {
        self.location = String.EMPTY
    }
    
    func saveLocation(location:Location) {
        sharedViewModel.saveLocation(location: location) { _, _ in
        }
        self.location = location.getFullTitle()
        self.state = ViewState<Locations>(isLoading: false, data: nil, error: nil)
    }
    
    func getLocation() {
        sharedViewModel.getLocation { location, error in
            if location != nil && error == nil {
                self.location = location?.getFullTitle() ?? String.EMPTY
            }
        }
    }
}

extension Location {
    func getFullTitle() -> String {
        return "\(name),\(region),\(country)"
    }
}
