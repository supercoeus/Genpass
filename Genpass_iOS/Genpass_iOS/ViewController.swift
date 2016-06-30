//
//  ViewController.swift
//  Genpass_iOS
//
//  Created by feix.chow on 6/22/16.
//  Copyright © 2016 feix.chow. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var informationTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var strongSwitch: UISwitch!

    @IBAction func passClear(sender: AnyObject) {
        let pasteBoard = UIPasteboard.generalPasteboard()
        pasteBoard.string = ""
    }

    @IBAction func passGenerator(sender : AnyObject) {
        let concat = "strongpassword"
        let shatext = usernameTextField.text! + concat + informationTextField.text! + concat + passwordTextField.text!
        let shapass = Array(shatext.sha256.characters)
        var pass = ""
        print(shatext.sha256.characters)
        for index in 0.stride(to: shapass.count, by: 2) {
            let num = Int(String(shapass[index]) + String(shapass[index+1]), radix:16) ?? 0
            if num < 127 && num > 32 && num != 92 && num != 96 && num != 34 && num != 39 {
                if strongSwitch.on {
                    pass = String(Character(UnicodeScalar(num))) + pass
                } else if (num < 58 && num > 47) || (num < 91 && num > 64) || (num < 123 && num > 96) {
                    pass = String(Character(UnicodeScalar(num))) + pass
                }
            }
        }

        let passCount = pass.characters.count
        if passCount < 7 {
            pass = pass + pass
        } else if passCount > 14 {
            pass = pass[pass.startIndex.advancedBy(passCount-14)...pass.startIndex.advancedBy(passCount-1)]
        }
        let pasteBoard = UIPasteboard.generalPasteboard()
        pasteBoard.string = pass
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

