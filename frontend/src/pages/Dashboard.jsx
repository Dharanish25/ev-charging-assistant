import { Box, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();
  const fullName = localStorage.getItem('fullName');

  const handleLogout = () => {
    localStorage.clear();
    navigate('/');
  };

  return (
    <Box p={4}>
      <Typography variant="h4">Welcome, {fullName}!</Typography>
      <Typography mt={2}>Dashboard coming soon — vehicles, map, bookings will go here.</Typography>
      <Button variant="outlined" sx={{ mt: 3 }} onClick={handleLogout}>
        Logout
      </Button>
    </Box>
  );
}

export default Dashboard;  