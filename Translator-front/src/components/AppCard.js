import React, { useState } from "react";
import axios from "axios";

const AppCard = ({ name, time, id }) => {

  const handleDownloadExcel = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/applications/download/${id}`,
        {
          responseType: "blob",
        }
      );

      const blob = new Blob([response.data], {
        type: response.headers["content-type"],
      });

      const url = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;

      link.setAttribute("download", `translator_${id}.csv`);

      document.body.appendChild(link);

      link.click();

      // Clean up
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);
    } catch (error) {}
  };

  const handleDeploy = async (event) => {
    const file = event.target.files[0];
    if (!file) {
      return;
    }
    const reader = new FileReader();
    reader.onload = (event) => {
      try {
        const jsonData = JSON.parse(event.target.result);
        const deployResponse = axios.post(`http://localhost:8080/api/translation-keys/${id}`, jsonData.entries)
        alert("Uploaded file successfully.")
      } catch (e) {
        console.error("Error reading the file", e);
        alert("Failed to upload file, please check that it is in the currect format and there are no duplications.")
      }
    };

    reader.onerror = (error) => {
      console.error("Error occurred while reading the file", error);
    };

    reader.readAsText(file);
  };

  const handleAddTranslationKey = async () => {
    const key = window.prompt('Enter Key:');
    const languageCode = window.prompt('Enter Language Code:');
    const text = window.prompt('Enter Text:');

    if (key && languageCode && text) {
      const data = [{
        key: key,
        translations: [
          {
            languageCode: languageCode,
            text: text
          }
        ]
      }];
      const addTranslationKeyResponse = axios.post(`http://localhost:8080/api/translation-keys/${id}`, data)
      alert("Added translationKey successfully.")
    } else {
      alert('Please fill in all fields');
    }
  }

  return (
    <div className="card mb-3">
      <div className="card-header bg-primary text-white">
        <h2 className="card-title">{name}</h2>
      </div>
      <div className="card-body">
        <p className="card-text">Last deployment: {time}</p>
        <div className="d-flex justify-content-between">
          <button onClick={handleDownloadExcel} className="btn btn-primary m-2">
            Download on xlsx
          </button>
          <label className="btn btn-secondary">
            Deploy
            <input
              type="file"
              onChange={handleDeploy}
              accept=".json"
              style={{ display: "none" }}
            />
          </label>
          <button onClick={handleAddTranslationKey} className="btn btn-primary m-2">
            Add translationKey
          </button>
        </div>
      </div>
    </div>
  );
};

export default AppCard;
