import Login from './Pages/Login/Login';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Registration from './Pages/Registration/Registration';
import Appointment from './Pages/Appointment';
import axios from "axios";


function App(){
    return(
        <div>
            <Router>
                <div>
                    <Switch>
                        <Route path = "/appointment" component = {Appointment}/>
                        <Route path = "/registration" component = {Registration}/>
                        <Route path = "/" component = {Login}/>
                    </Switch>
                </div>
            </Router>
        </div>
    )
}

export default App;
