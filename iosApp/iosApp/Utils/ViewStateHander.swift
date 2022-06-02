//
//  ViewStateHander.swift
//  iosApp
//
//  Created by Никита on 31.05.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI


struct ViewStateHandler<D,T:ViewState<D>,Content:View,Error:View,Loading:View> :View {
    
    var state:ViewState<D>
    let content: (D) -> Content
    let error: (String) -> Error
    let loading: () -> Loading
    
    
    init(
        state:ViewState<D>,
        @ViewBuilder content: @escaping (D)-> Content,
        @ViewBuilder error: @escaping (String) ->Error,
        @ViewBuilder loading: @escaping ()->Loading
    ) {
        self.state = state
        self.content = content
        self.error = error
        self.loading = loading
    }
    
    var body: some View {
        if state.isLoading {
            loading()
        }
        
        if state.data != nil {
            content(state.data!)
        }
        
        if state.error != nil  {
            error(state.error!.message!)
        }
        
    }
}

struct DefaultLoadingView: View {
    var body: some View {
        ProgressView()
    }
}

struct DefaultErrorView: View {
    
    let errorMessage:String
    
    var body: some View {
        VStack{
            
            Text("We're sorry")
            Text(errorMessage)
        }
    }
}
