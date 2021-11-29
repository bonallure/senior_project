import Clinic from "./clinic3.png"

function Logo(){
    return(
        <div style = {{fontWeight:"bold"}}>
            <img src = {Clinic} alt="" width="75px" height="60px"/>
            Clinic Online
        </div>
    )
}

export default Logo;