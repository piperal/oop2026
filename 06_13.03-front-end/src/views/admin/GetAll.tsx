import { useEffect, useState } from 'react'
import type { Athlete } from '../../models/Athlete'

function GetAll() {

  const [athletes, setAthletes] = useState<Athlete[]>([])
  const [newAthlete, setNewAthlete] = useState({ firstName: "", lastName: "", number: 0, fieldName: "", result: 0 })

  useEffect(() => {
    fetch("http://localhost:5000/all").then(res => res.json()).then(data => {
      setAthletes(data.content);
    })
  }, [])


  function deleteAthlete(id: number) {
    fetch(`http://localhost:5000/delete/${id}`, {
      method: "DELETE"
    }).then(res => res.json().then(data => setAthletes(data)))
  }

  const addAthlete = () => {
    const addAthlete = `{ "firstName": "${newAthlete.firstName}", "lastName": "${newAthlete.lastName}", "number": ${newAthlete.number}, "results": [{"fieldName": "${newAthlete.fieldName}", "result": ${newAthlete.result}}] }`
    console.log(addAthlete)
    fetch("http://localhost:5000/add", {
      method: "POST",
      body: addAthlete,
      headers: {
        "Content-Type": "application/json"
      }
    }).then(res => res.json().then(data => setAthletes(data)))
  }



  return (<>
    <div id='addContainer'>
      <input id='fname' onChange={(e) => { setNewAthlete({ ...newAthlete, firstName: e.target.value }) }} placeholder='Enter first name'></input>
      <input id='fname' onChange={(e) => { setNewAthlete({ ...newAthlete, lastName: e.target.value }) }} placeholder='Enter last name'></input>
      <input id='fname' onChange={(e) => { setNewAthlete({ ...newAthlete, number: Number(e.target.value) }) }} placeholder='Enter number'></input>
      <input id='fieldName' onChange={(e) => { setNewAthlete({ ...newAthlete, fieldName: e.target.value }) }} placeholder='Enter field name'></input>
      <input id='result' onChange={(e) => { setNewAthlete({ ...newAthlete, result: parseInt(e.target.value) }) }} placeholder='Enter result'></input>
      <button onClick={() => { addAthlete() }}>Add new Athlete</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Number</th>
          <th>Results</th>
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
            <td>{`${athlete.results[0].result}`}</td>
            <td><button onClick={() => { deleteAthlete(athlete?.id) }}>Delete</button></td>
          </tr>)}
      </tbody>
    </table>
  </>
  )
}

export default GetAll