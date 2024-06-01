import { useEffect, useState } from 'react';
import './App.css';
import axios from 'axios';
import ClientsTable from './components/ClientComponents/ClientsTable';
import ClientsForm from './components/ClientComponents/ClientsForm';
import VideojuegosForm from './components/VideojuegoComponents/VideojuegosForm';
import VideojuegosTable from './components/VideojuegoComponents/VideojuegosTable';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import VentasList from "./components/VentaComponents/VentasList";
import VentaDetails from "./components/VentaComponents/VentaDetails";
import AddVenta from "./components/VentaComponents/AddVenta";
import Register from './components/RegisterLoginComponents/Register';
import Login from './components/RegisterLoginComponents/Login';

function App() {
  const [clientes, setClientes] = useState([]);
  const [editingCliente, setEditingCliente] = useState(null);
  const [videojuegos, setVideojuegos] = useState([]);
  const [editingVideojuego, setEditingVideojuego] = useState(null);
  const [darkMode, setDarkMode] = useState(false); // Modo claro por defecto
  const [isAuthenticated, setIsAuthenticated] = useState(false); // Estado de autenticación

  // Manejar la autenticación del usuario
  const handleLogin = () => {
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    setIsAuthenticated(false);
  };

  // Funciones clientes
  useEffect(() => {
    fetchClientes();
  }, []);

  const handleCreateOrUpdateCliente = async (clienteData) => {
    if (editingCliente) {
      await axios.put(`http://localhost:8080/api/clientes/${editingCliente.clienteId}`, clienteData);
    } else {
      await axios.post(`http://localhost:8080/api/clientes`, clienteData);
    }
  };

  const fetchClientes = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/clientes`);
      setClientes(response.data);
    } catch (error) {
      console.log('Error al cargar los clientes:', error);
    }
  };

  const handleEditCliente = (cliente) => {
    setEditingCliente(cliente);
  };

  const handleDeleteCliente = async (clienteId) => {
    await axios.delete(`http://localhost:8080/api/clientes/${clienteId}`);
    fetchClientes();
  };

  // Funciones videojuegos
  useEffect(() => {
    fetchVideojuegos();
  }, []);

  const handleCreateOrUpdateVideojuego = async (videojuegoData) => {
    if (editingVideojuego) {
      await axios.put(`http://localhost:8080/api/videojuegos/${editingVideojuego.id}`, videojuegoData);
    } else {
      await axios.post(`http://localhost:8080/api/videojuegos`, videojuegoData);
    }
  };

  const fetchVideojuegos = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/videojuegos`);
      setVideojuegos(response.data);
    } catch (error) {
      console.log('Error al cargar los videojuegos:', error);
    }
  };

  const handleEditVideojuego = (videojuego) => {
    setEditingVideojuego(videojuego);
  };

  const handleDeleteVideojuego = async (id) => {
    await axios.delete(`http://localhost:8080/api/videojuegos/${id}`);
    fetchVideojuegos();
  };

  // Función para alternar el modo
  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
  };

  return (
    <div className='App'>
      <h1>Api Videojuegos</h1>
      {!isAuthenticated ? (
        <>
          <Register />
          <Login onLogin={handleLogin} />
        </>
      ) : (
        <>
          <button onClick={toggleDarkMode}>
            {darkMode ? 'Modo Claro' : 'Modo Oscuro'}
          </button>
          <button onClick={handleLogout}>Logout</button>
          <div className={`App ${darkMode ? 'dark-mode' : ''}`}>
            <h2>Lista de Clientes</h2>
            <ClientsTable clientes={clientes} onEdit={handleEditCliente} onDelete={handleDeleteCliente} />
            <h2>{editingCliente ? 'Editar Cliente' : 'Crear Cliente'}</h2>
            <ClientsForm onSubmit={handleCreateOrUpdateCliente} initialCliente={editingCliente} />
            <br />
            <h2>Lista de Videojuegos</h2>
            <VideojuegosTable videojuegos={videojuegos} onEdit={handleEditVideojuego} onDelete={handleDeleteVideojuego} />
            <h2>{editingVideojuego ? 'Editar Videojuego' : 'Crear Videojuego'}</h2>
            <VideojuegosForm onSubmit={handleCreateOrUpdateVideojuego} initialVideojuego={editingVideojuego} />
            <br />
            <Router>
              <Routes>
                <Route exact path="/ventas" element={<VentasList />} />
                <Route path="/" element={<AddVenta />} />
                <Route path="/ventas/:id" element={<VentaDetails />} />
              </Routes>
            </Router>
          </div>
        </>
      )}
    </div>
  );
}

export default App;