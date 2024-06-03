import React from 'react'

const TabButton = ({action, title}) => {
  return (
    <button className="App-link btn btn-primary mr-2" onClick={action}>{title}</button>
  )
}

export default TabButton