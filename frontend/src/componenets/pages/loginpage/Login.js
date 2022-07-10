import logo from "./darksloth-removebg-preview.png";
import {Button} from "reactstrap";
import React from "react";
import "./login.css"
import axios from "axios";

const Login = ({loginFunc}) => {
    return (
        <div className="info-container">
            <div className="brand-logo">
            <img src={logo} alt={"d"}/>
            </div>
            <div className="text-field">
                <h1>AssetSloth</h1>
                <h3> - Asset Automation Software </h3>
            </div>
            <div className="logindiv">
                <a onClick={loginFunc}>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    Login
                </a>
            </div>

        </div>
    );
}

export default Login