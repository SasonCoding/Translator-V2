import React, { useState, useEffect } from "react";
import AppCard from "../components/AppCard";
import SideBar from "../components/SideBar";
import axios from "axios";

const Home = () => {
  const [applications, setApplications] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchApplications();
  }, []);

  const handleCreateApplication = async () => {
    const appName = window.prompt('Enter the application name:');
    if (!appName) {
      return;
    }
    try {
      const response = await axios.post("http://localhost:8080/api/applications", { "name": appName })
      setApplications([...applications, response.data]);
    } catch (error){ 
      alert("Failed to save the application.")
      console.log(error);
    }
  }
  
  const fetchApplications = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/applications"
      );
      const data = response.data;
      setApplications(data);
      setLoading(false);
    } catch (error) {
      setError(error);
      setLoading(false);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div className="container-fluid d-flex flex-column align-items-center">
      <div className="row w-100 flex-grow-1">
        <SideBar />
        <div className="col-10 main">
          <h1 className="text-center">Translator Manager</h1>
          <div className="row justify-content-center">
            {applications.map((app) => (
              <div className="col-md-4" key={app.id}>
                <AppCard name={app.name} time={app.time} id={app.id} />
              </div>
            ))}
          </div>
          <div className="d-flex justify-content-center">
            <button onClick={handleCreateApplication} className="col-md-2 btn btn-primary btn-lg mt-3">
              Add app
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
