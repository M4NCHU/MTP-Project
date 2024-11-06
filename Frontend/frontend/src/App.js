import React, { useState } from 'react';

import './App.css';
import TabButton from './components/TabButton';
import FilmList from './components/tabs/FilmList';
import GenreList from './components/tabs/GenreList';

import LogList from './components/tabs/LogsList';
import LoginTab from './components/tabs/LoginTab';


function App() {
  const [activeTab, setActiveTab] = useState('tab1');

  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };

  const tabs = [
    {
      action: "tab1",
      title: "Lista filmów"
    },
    {
      action: "tab2",
      title: "Gatunki"
    },
    {
      action: "tab3",
      title: "Oceny"
    },
    {
      action: "tab4",
      title: "Logi"
    },
    {
      action: "tab5",
      title: "Logowanie"
    },
  ]

  return (
    <div className="App">
      <header className="">
        <div className='d-flex p-4 flex-row justify-content-center gap-4'>
          {tabs.map((t, i) => (
            <TabButton key={i} action={() => handleTabClick(t.action)} title={t.title}/>
          ))}
        
        </div>
        
      </header>
      <div className='container'>
      {activeTab === 'tab1' && <FilmList/>}
        {activeTab === 'tab2' && <GenreList/>}
        {activeTab === 'tab3' && <div><h1>Content of Tab 3</h1></div>}
        {activeTab === 'tab4' && <LogList/>}
        {activeTab === 'tab5' && <LoginTab/>}
      </div>
      
    </div>
  );
}

export default App;
