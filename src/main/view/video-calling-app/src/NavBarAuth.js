import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';

export default function NavBarAuth() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" elevation={0}>
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Clinic Online
          </Typography>
          <Box sx={{flexGrow: 1, alignSelf: 'center'}}>
            <Button color="inherit" sx={{px:5}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/dashboard';}}>Dashboard</Button>
            <Button color="inherit" sx={{px:5}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/calendar';}}>Calendar</Button>
            <Button color="inherit" sx={{px:5}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/appointment';}}>Appointment</Button>
            <Button color="inherit" sx={{px:5}} onClick={(e) => {
                e.preventDefault();
                window.location.href='/message';}}>Message</Button>
            <Button color="inherit" sx={{px:5}} onClick={(e) => {
                e.preventDefault();
                window.location.href='http://localhost:3006/';}}>Join Call</Button>
          </Box>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
