import axios from "axios";
import React from "react";
import NavBar from "../../Components/NavBar/NavBar";
import "./Registration.css"

class Registration extends React.Component{
    state = {
        firstName: '',
        lastName: '',
        dateOB: '', 
        email: ''
    }

    
    render(){
        return(
            <div>
                <NavBar/>
                <div className = "userRequest">
                    <h1> New User Request </h1>
                    <br />
                    <br />
                    <form>
                        <input type = "text" id = "firstName" placeHolder = "First Name"/>
                        <br />
                        <br />
                        <input type = "text" id = "lastName" placeHolder = "Last Name"/>
                        <br />
                        <br />
                        <input type = "text" id = "dateOB" placeHolder = "MM/DD/YYYY"/>
                        <br />
                        <br />
                        <input type = "text" id = "email" placeHolder = "Email"/>
                        <br />
                        <br />
                        <br />
                        <button onSubmit = {this.handleSubmit}> Submit </button>
                    </form>
                </div>
            </div>
        )
    }
}

export default Registration;
// const Registration = ({ register }) => {
//     const history = useHistory();
//     const onSubmitHandler = async (e) => {
//         e.prventDefault();

//     const registrationObj = {
//         firstName = e.target[0].value,
//         lastName = e.target[1].value,
//         dateOB = e.target[3].value,
//         email = e.target[4].value,
//     };
//     };

//     return (
//         <div>
//             <p> New User Request </p>
//             <div>
//                 <form onSubmit = {onSubmitHandler}>
//                 <Input type = "text" id = "firstName" />  
//                 <Input type = "text" id = "lastName" />
//                 <br />
//                 <Input type = "text" id = "dateOB" />
//                 <br />
//                 <Input type = "text" id = "email" />
//                 </form>
//             </div>
//         </div>
//     );
// }