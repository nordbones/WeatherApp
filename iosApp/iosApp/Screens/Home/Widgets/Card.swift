//
//  Card.swift
//  iosApp
//
//  Created by Никита on 2.06.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI


struct Card :View {
    
    let label:String
    let value:String
    let units:String
    
    var body: some View {
        VStack {
            Text(label)
                .foregroundColor(Color.white)
            Text(value)
                .foregroundColor(Color.white)
            Text(units)
                .foregroundColor(Color.white)
        }
        .frame(minWidth: 0,maxWidth: .infinity, alignment: .center)
        .padding()
        .background(Color.blue)
        .clipShape(RoundedRectangle(cornerRadius: 10))
    }
}
