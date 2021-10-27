import './App.css';
import { Component } from 'react';
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

export default App;
