import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import HomeIcon from '@mui/icons-material/Home';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Container from '@mui/material/Container';

class NavBarAuth extends React.Component{

  constructor(props) {
    super(props);
    const {cookies} = props;
    // this.state.csrfToken = cookies.get('XSRF-TOKEN');
    this.state = {};
  }

  onClick = (e) => {
    e.preventDefault();
    this.props.props.history.push(e.target.id);
  }

  render(){

    const toolbarStyle = {
      background: "lightgray",
      left: 0,
      bottom: 0,
      width: "100%"
    }

    return (
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static" elevation={0}>
          <Toolbar sx={toolbarStyle}>
            <IconButton size="xlarge" edge="start" color="inherit" aria-label="home">
              <HomeIcon onClick={(e) => {
                e.preventDefault();
                window.location.href='/';}}/>
            </IconButton>
            <Typography variant="h4" component="div">
              Clinic Online
            </Typography>
            <Box sx={{flexGrow: 1, alignSelf: 'center'}}>
              <Button color="inherit" sx={{px:10}} onClick={this.onClick} id='/doctor/dashboard'>Dashboard</Button>
              <Button color="inherit" sx={{px:10}} onClick={this.onClick} id='/doctor/calendar'>Calendar</Button>
              <Button color="inherit" sx={{px:10}} onClick={this.onClick} id='/doctor/appointments'>Appointments</Button>
              <Button color="inherit" sx={{px:10}} onClick={this.onClick} id="/doctor/messages">Messages</Button>
              <Button color="inherit" sx={{px:10}} onClick={this.onClick} id="/doctor/prescriptions">Prescriptions</Button>
              <Button color="inherit" sx={{px:10}} onClick={this.onClick} id='http://localhost:3006/'>Join Call</Button>
            </Box>
            <AccountCircleIcon sx={{pr: 1, fontSize: 35}}/>
          </Toolbar>
        </AppBar>
      </Box>
    );
  }
}

export default  NavBarAuth;
