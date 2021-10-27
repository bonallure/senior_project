import axios from "axios"
import Button from "../Componets/Button";
import Input from "../Componets/Input";
import { useHistory } from "react-router";

const Login = ({ login }) => {
    const history = useHistory();
    const onSubmitHandler = async (e) => {
        e.preventDefault();

    const loginObj = {
        email: e.target[0].value,
        password: e.target[1].value,
    };
    loginObj(loginObj, history);
    };

    return(
        <div>
            <p> Login </p>
            <div></div>
            <form onSubmit = {onSubmitHandler}>
                <Input type = "text" id = "email" />
                <br />
                <Input type = "text" id = "password" />
                <br />
                <br />
                <Button type = "submit" id = "loginBtn" btnName = "Login" />
            </form>
        </div>
    );
};

export default Login;