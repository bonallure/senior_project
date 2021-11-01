import React from "react";
import NavBar from "../Componets/NavBar/NavBar";

class Homepage extends React.Component{
    // declaring state
    state = {
        isLoggedIn: "",
        password: ''
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.props.history.push("/patient/login")
    }

    render(){
        return(
            <div>
                <NavBar/>
                <div className="mainPaper" style={{textAlign:"center"}}>
                    <p> Welcome to Clinic Online </p>
                    <button onClick={this.onSubmit}>Login</button>
                </div>
            </div>
        )
    }
}

export default Homepage;