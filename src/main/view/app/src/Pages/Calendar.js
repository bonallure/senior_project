import React from "react";
import NavBarAuth from "../Components/NavBar/NavBarAuth";

class Calendar extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            userId: ""
        }
        this.state.userId = props.userId;
    }
    render(){
        return(
            <div>
                <NavBarAuth/>
            </div>
        )
    }
}

export default Calendar;