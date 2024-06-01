const ClientsRow = ({ cliente, onEdit, onDelete }) => {
    const handleEdit = () => {
      onEdit(cliente);
    };
  
    const handleDelete = () => {
      onDelete(cliente.clienteId);
    };
  
    return (
      <tr>
        <td>{cliente.nombres}</td>
        <td>{cliente.email}</td>
        <td>{cliente.telefono}</td>
        <td>{cliente.direccion}</td>
        <td>{cliente.contrasena}</td>
        <td>{cliente.numeroIdentificacion}</td>
        <td>
          <button onClick={handleEdit}>Editar ✏️</button>
          <button onClick={handleDelete}>Eliminar 🗑️</button>
        </td>
      </tr>
    );
  };
  
  export default ClientsRow;