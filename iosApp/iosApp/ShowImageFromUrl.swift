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
    func loadImage(url: String) {
        Nuke.loadImage(
            with: ImageRequest(url: URL(string: url)!),
            into: self
        )
    }
}
