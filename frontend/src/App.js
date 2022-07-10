import './App.css';
import React, {useState, useEffect} from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import NewEmployee from "./componenets/pages/assignasset/NewEmployee";
import {Button} from "reactstrap";
import Login from "./componenets/pages/loginpage/Login";


function App() {

    const [authenticated, setAuthenticated] = useState(false);
    const [loading, setLoading] = useState(false);
    const [user, setUser] = useState(undefined);
    // const [cookies] = useCookies(['XSRF-TOKEN']);

    useEffect(() => {
        setLoading(true);
        fetch('/v1/user')
            .then(response => response.text())
            .then(body => {
                if (body === '') {
                    setAuthenticated(false);
                } else {
                    setUser(JSON.parse(body));
                    setAuthenticated(true);
                }
                setLoading(false);
            });
    }, [setAuthenticated, setLoading, setUser])

    const login = () => {
        let port = (window.location.port ? ':' + window.location.port : '');
        console.log("port" + port)
        if (port === ':3000') {
            port = ':8080';
        }
        window.location.href = `//${window.location.hostname}${port}/private`;
    }


    const button = authenticated ?
       <NewEmployee /> :  <Login loginFunc={login}/>

return (
    <>
        {button}

    </>
    // <Router>
    //     <Routes>
    //     <Route exact path="/" element={<NewEmployee/>} />
    //     <Route exact path="/custom" element={<CustomEmployee />} />
    //     </Routes>
    // </Router>
)

}

export default App;
