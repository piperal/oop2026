import { useEffect, useState } from 'react'
import type { Athlete } from '../../models/Athlete'
import type { Result } from '../../models/result'

function GetAll() {

  const [athletes, setAthletes] = useState<Athlete[]>([])
  const [ediAthlete, setEditAthlete] = useState<Result>({ fieldName: "", result: 0 })
  const [newAthlete, setNewAthlete] = useState({ firstName: "", lastName: "", number: 0 })

  useEffect(() => {
    fetch("http://localhost:5000/all").then(res => res.json()).then(data => setAthletes(data))
  }, [])


  function editAthlete(id: number) {
    fetch(`http://localhost:5000/results/update/${id}`, {
      method: "PUT",
      body: JSON.stringify(ediAthlete),
      headers: {
        "Content-Type": "application/json"
      }
    }
    ).then(res => res.json().then(data => setAthletes(data)))
  }

  function deleteAthlete(id: number) {
    fetch(`http://localhost:5000/delete/${id}`, {
      method: "DELETE"
    }).then(res => res.json().then(data => setAthletes(data)))
  }


  const addAthlete = () => {
    fetch("http://localhost:5000/add", {
      method: "POST",
      body: JSON.stringify(newAthlete),
      headers: {
        "Content-Type": "application/json"
      }
    }).then(res => res.json().then(data => setAthletes(data)))
  }

  return (<>

    <div id='editContainer'>
      <input id='fieldName' onChange={(e) => { setEditAthlete({ ...ediAthlete, fieldName: e.target.value }) }} placeholder='Enter field name'></input>
      <input id='result' onChange={(e) => { setEditAthlete({ ...ediAthlete, result: parseInt(e.target.value) }) }} placeholder='Enter result'></input>
    </div>
    <div id='addContainer'>
      <input id='fname' onChange={(e) => { setNewAthlete({ ...newAthlete, firstName: e.target.value }) }} placeholder='Enter first name'></input>
      <input id='fname' onChange={(e) => { setNewAthlete({ ...newAthlete, lastName: e.target.value }) }} placeholder='Enter last name'></input>
      <input id='fname' onChange={(e) => { setNewAthlete({ ...newAthlete, number: Number(e.target.value) }) }} placeholder='Enter number'></input>
      <button onClick={() => { addAthlete() }}>Add new Athlete</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Number</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        {athletes.map(athlete =>
          <tr key={athlete.id}>
            <td>{athlete.id}</td>
            <td>{athlete.firstName}</td>
            <td>{athlete.lastName}</td>
            <td>{athlete.number}</td>
            <td><button onClick={() => { editAthlete(athlete.id) }}>Edit</button></td>
            <td><button onClick={() => { deleteAthlete(athlete?.id) }}>Delete</button></td>
          </tr>)}
      </tbody>
    </table>
  </>
  )
}

export default GetAll