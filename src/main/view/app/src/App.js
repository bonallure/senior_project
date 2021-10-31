import React from "react";
import Homepage from "./Pages/Homepage";
import NavBar from "./Componets/NavBar/NavBar";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Appointment from "./Pages/Appointment";
import Registration from "./Pages/Registration/Registration";
import Login from "./Pages/Login/Login";


function App(){

    return (
        <div>
            <Router>
                <NavBar/>
                <div className="container">
                    <Switch>
                        <Route path = "/appointment" component = {Appointment}/>
                        <Route path = "/registration" component = {Registration}/>
                        <Route path = "/login" component = {Login}/>
                        <Route path = "/" component = {Homepage}/>
                    </Switch>
                </div>
            </Router>
        </div>
    )
}

export default App;
