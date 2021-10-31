import Login from './Pages/Login/Login';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';


function App(){
    return(
        <div>
            <Router>
                <div>
                    <Switch>
                        <Route path = "/" component = {Login}/>
                        <Login/>
                    </Switch>
                </div>
            </Router>
        </div>
    )
}

export default App;