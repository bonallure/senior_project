import './App.css';
import { Component } from 'react';
<<<<<<< HEAD
import Login from './Pages/Login';
import { Redirect, Route } from "react-router-dom";
import axios from "axios";

class App extends Component {
  constructor(){
    super();
    this.state = {
      user: {},
  }

  login = async (loginObj) => {
    const response = await axios.post("/", loginObj);
    console.log(response.data.data);
    this.setState({ user: response.data.data }, () => {
      history.push("/");
    });
  };

  render()
  {
    return(
      <div>
        <Route exact path = '/' Component = {Login} ></Route>
      </div> 
    );
  }
}}
=======
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
// class App extends Component {
//   constructor(){
//     super();
//     this.state = {
//       user: {},
//   }

//   login = async (loginObj) => {
//     const response = await axios.post("/", loginObj);
//     console.log(response.data.data);
//     this.setState({ user: response.data.data }, () => {
//       history.push("/");
//     });
//   };

//   render()
//   {
//     return(
//       <div>
//         <Route exact path = '/' Component = {Login} ></Route>
//       </div> 
//     );
//   }
// }}
>>>>>>> origin/frontend

export default App;
