import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import HomeIcon from '@mui/icons-material/Home';

export default function NavBarAuth() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <HomeIcon onClick={(e) => {
              e.preventDefault();
              window.location.href='/';}}/>
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Clinic Online
          </Typography>
          <Button color="inherit" onClick={(e) => {
              e.preventDefault();
              window.location.href='/calendar';}}>Calender</Button>
          <Button color="inherit" onClick={(e) => {
              e.preventDefault();
              window.location.href='/appointment';}}>Appointment</Button>
          <Button color="inherit" onClick={(e) => {
              e.preventDefault();
              window.location.href='/message';}}>Message</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
