import { Link, Routes, Route } from 'react-router-dom'
import GetAll from './views/admin/GetAll'
import Homepage from './views/Homepage'
import './App.css'

function App() {

  return (
    <>
      <Link to="/athletes">
        <button>Athletes</button>
      </Link>
      <Link to="/">
        <button>Home</button>
      </Link>

      <Routes>
        <Route path='/athletes' element={<GetAll />}></Route>
        <Route path='/' element={<Homepage />}></Route>
      </Routes>
    </>
  )
}

export default App
