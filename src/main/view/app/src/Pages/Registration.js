import axios from "axios";
import Input from "../../Components/Input/Input";
import Button from "../../Components/Button/Button";
import { useHistory } from "react-router";

const Registration = ({ register }) => {
    const history = useHistory();
    const onSubmitHandler = async (e) => {
        e.prventDefault();

    const registrationObj = {
        firstName = e.target[0].value,
        lastName = e.target[1].value,
        dateOB = e.target[3].value,
        email = e.target[4].value,
    };
    };

    return (
        <div>
            <p> New User Request </p>
            <div>
                <form onSubmit = {onSubmitHandler}>
                <Input type = "text" id = "firstName" />  
                <Input type = "text" id = "lastName" />
                <br />
                <Input type = "text" id = "dateOB" />
                <br />
                <Input type = "text" id = "email" />
                <br />
                <br />
                <Button type = "submit" id = "registerBtn" btnName = "newUserRequest" />
                </form>
            </div>
        </div>
    );
}