//
//  iOSSpeaker.swift
//  iosApp
//
//  Created by Marcin Moskala on 05/03/2019.
//

import Foundation
import app
import AVKit
import AVFoundation

class iOSSpeaker: Speaker {
    let synthesizer = AVSpeechSynthesizer()
    
    func speak(text: String) {
        synthesizer.stopSpeaking(at: .word)
        let utterance = AVSpeechUtterance(string: text)
        synthesizer.speak(utterance)
    }
    
    func playWhistle() {
        
    }
    
    func playEndSound() {
        
    }
}
