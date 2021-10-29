import Login from './Pages/Login/Login';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import axios from "axios";


function App(){
    return(
        <div>
            <Router>
                <div>
                    <Switch>
                        <Route path = "/" component = {Login}></Route>
                        <Login/>
                    </Switch>
                </div>
            </Router>
        </div>
    )
}

export default App;
