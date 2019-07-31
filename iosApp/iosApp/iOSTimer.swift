//
//  iOSTimer.swift
//  iosApp
//
//  Created by Marcin Moskala on 05/03/2019.
//

import Foundation
import app

class iOSTimer: app.Timer {
    
    var timer: Foundation.Timer? = nil
    
    func start(seconds: Int32, onTick: @escaping (KotlinInt) -> (), onFinish: @escaping () -> ()) {
        stop()
        _ = onTick(KotlinInt(int: seconds))
        var secondsLeft = seconds
        _onTick = {
            secondsLeft -= 1
            if secondsLeft > 0 {
                _ = onTick(KotlinInt(int: secondsLeft))
            } else {
                _ = onFinish()
            }
        }

        timer = Foundation.Timer.scheduledTimer(
            timeInterval: 1,
            target: self,
            selector: #selector(self.onTickFunction),
            userInfo: nil,
            repeats: true
        )
    }
    
    func stop() {
        if let timer = timer {
            timer.invalidate()
        }
    }
    
    var _onTick: ()->() = {}
    @objc func onTickFunction() {
        _onTick()
    }
}
