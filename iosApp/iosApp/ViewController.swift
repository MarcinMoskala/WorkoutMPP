import UIKit
import app
import SnapKit

class ViewController: UIViewController {
    
    private lazy var viewModel = WorkoutViewModel(timer: iOSTimer(), speaker: iOSSpeaker())
    
    lazy var titleView = UILabel()
    lazy var imageView = UIImageView()
    lazy var progressView = UIProgressView()
    lazy var timerView = UILabel()
    lazy var nextContainer = UIView()
    lazy var nextView = UIImageView(image: UIImage(named: "next"))
    lazy var prevContainer = UIView()
    lazy var prevView = UIImageView(image: UIImage(named: "prev"))
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
        titleView.bindText(with: viewModel.titleProp)
        timerView.bindText(with: viewModel.timerTextProp)
        
        viewModel.imgApiUrlProp.addListenerTyped { (url: String) in
            self.imageView.loadImage(url: ExercisesRepositoryKt.BASE_URL + "/images/" + url)
        }
        viewModel.progressProp.addListenerTyped { (progress: Int) in
            self.progressView.progress = Float(progress) / 100
        }
        nextView.addTapGestureRecognizer { self.viewModel.onNext() }
        prevView.addTapGestureRecognizer { self.viewModel.onPrevious() }
        viewModel.onStart()
    }
}

extension MutableProp {
    func addListenerTyped<T>(listener: @escaping (T)->()) {
        addListener { elem in
            listener(elem as! T)
            return KotlinUnit()
        }
    }
}

extension UILabel {
    func bindText(with prop: MutableProp) {
        prop.addListenerTyped { (text: String) in
            self.text = text
        }
    }
}

extension ViewController {
    
    func setupView() {
        view.backgroundColor = UIColor.white
        
        titleView.textAlignment = .center
        titleView.font = UIFont(name: titleView.font.fontName, size: 24)
        view.addSubview(titleView)
        
        imageView.contentMode = .scaleAspectFit
        view.addSubview(imageView)
        
        view.addSubview(progressView)
        
        timerView.textAlignment = .center
        titleView.font = UIFont(name: titleView.font.fontName, size: 24)
        view.addSubview(timerView)
        
        nextContainer.addSubview(nextView)
        view.addSubview(nextContainer)
        prevContainer.addSubview(prevView)
        view.addSubview(prevContainer)
        
        titleView.snp.makeConstraints { make in
            make.right.left.equalToSuperview()
            make.margins.equalTo(60)
            make.height.equalTo(120)
            make.margins.equalTo(16)
        }
        
        imageView.snp.makeConstraints { make in
            make.right.left.equalToSuperview()
            make.top.equalTo(titleView.snp.bottom)
            make.bottom.equalTo(progressView.snp.top)
        }
        
        progressView.snp.makeConstraints { make in
            make.right.left.equalToSuperview()
            make.bottom.equalTo(timerView.snp.top)
            make.height.equalTo(5)
            make.margins.equalTo(10)
        }
        
        timerView.snp.makeConstraints { make in
            make.right.equalTo(nextContainer.snp.left)
            make.left.equalTo(prevContainer.snp.right)
            make.bottom.equalToSuperview()
            make.height.equalTo(100)
        }
        
        nextContainer.snp.makeConstraints { make in
            make.right.equalToSuperview()
            make.top.equalTo(timerView.snp.top)
            make.bottom.equalToSuperview()
            make.width.equalTo(60)
        }
        
        prevContainer.snp.makeConstraints { make in
            make.left.equalToSuperview()
            make.top.equalTo(timerView.snp.top)
            make.bottom.equalToSuperview()
            make.width.equalTo(60)
        }
        
        prevView.snp.makeConstraints { make in
            make.centerX.centerY.equalToSuperview()
            make.height.width.equalTo(40)
        }
        
        nextView.snp.makeConstraints { make in
            make.centerX.centerY.equalToSuperview()
            make.height.width.equalTo(40)
        }
        
        view.setNeedsUpdateConstraints()
    }
}
