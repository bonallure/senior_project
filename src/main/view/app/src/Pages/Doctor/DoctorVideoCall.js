import React from "react";  
import NavBarAuthDoc from "../../Components/NavBar/NavBarAuthDoc";
import axios from "axios";
import {Paper} from "@mui/material";
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import { VideoCallDiv } from "../../Components/VideoCallDiv";

class DoctorVideoCall extends React.Component{
  constructor(props) {
    console.log("we in this thang");
    super(props)
    const {cookies} = props;
    // this.state.csrfToken = cookies.get('XSRF-TOKEN');
    // console.log(props.user);
    this.state = {
    //     hasError: false,
      user: props.user,
      patient: props.patient
    };
  }
  
  render(){
    return(
      <div>
          <NavBarAuthDoc props={this.props}/>
          <VideoCallDiv/>
      </div>
    )
  }
}

export default DoctorVideoCall;