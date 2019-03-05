import UIKit
import app
import SnapKit

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = UIColor.white
        
        let questionView = UILabel()
        questionView.textAlignment = .center
        questionView.text = "Question"
        
        view.addSubview(questionView)
        
        let button = UIButton(type: UIButtonType.system)
        view.addSubview(button)
        button.setTitle("OK", for: .normal)
        
        questionView.snp.makeConstraints { make in
            make.top.right.left.equalToSuperview()
            make.bottom.equalTo(button.snp.top)
        }
        
        button.snp.makeConstraints { make in
            make.height.equalTo(100)
            make.bottom.equalToSuperview()
            make.right.equalToSuperview()
            make.left.equalToSuperview()
        }
        
        view.setNeedsUpdateConstraints()
    }
    
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        label.text = Proxy().proxyHello()
//    }
}
