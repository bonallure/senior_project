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

export default function NavBarAuth() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" elevation={0}>
        <Toolbar>
          <IconButton size="xlarge" edge="start" color="inherit" aria-label="home">
            <HomeIcon onClick={(e) => {
              e.preventDefault();
              window.location.href='/';}}/>
          </IconButton>
          <Typography variant="h4" component="div">
            Clinic Online
          </Typography>
          <Box sx={{flexGrow: 1, alignSelf: 'center'}}>
            <Button color="inherit" sx={{px:10}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/dashboard';}}>Dashboard</Button>
            <Button color="inherit" sx={{px:10}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/calendar';}}>Calendar</Button>
            <Button color="inherit" sx={{px:10}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/appointment';}}>Appointment</Button>
            <Button color="inherit" sx={{px:10}} onClick={(e) => {
              e.preventDefault();
              window.location.href='/message';}}>Message</Button>
            <Button color="inherit" sx={{px:10}} onClick={(e) => {
              e.preventDefault();
              window.location.href='http://localhost:3006/';}}>Join Call</Button>
          </Box>
          <AccountCircleIcon sx={{pr: 1, fontSize: 35}}/>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
