//
//  ShowImageFromUrl.swift
//  iosApp
//
//  Created by Marcin Moskala on 10/03/2019.
//

import UIKit
import Foundation
import Nuke

extension UIImageView {
    func loadImage(url strUrl: NSString?) {
        if let strUrl = strUrl {
            if let url = URL(string: String(strUrl)) {
                Nuke.loadImage(
                    with: ImageRequest(url: url),
                    into: self
                )
            }
        }
    }
}
