# JDBC and Selenium Automation Example

## Overview

This project demonstrates how to connect to a MySQL database using JDBC and use Selenium WebDriver to perform automated login actions on a web application. The program retrieves user credentials from the database and uses them to log into a specified website.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Code Explanation](#code-explanation)
- [License](#license)

## Features

- Connects to a MySQL database using JDBC.
- Retrieves user credentials from the `userdetail` table.
- Automates login actions on a specified web application using Selenium WebDriver.
- Validates the page title post-login to confirm successful login.

## Technologies Used

- Java 17
- JDBC (Java Database Connectivity)
- MySQL Database
- Selenium WebDriver
- WebDriverManager

## Prerequisites

Before running the project, ensure you have the following installed:

- JDK (Java Development Kit)
- MySQL Server
- Maven (for managing dependencies)
- Chrome Browser (with the latest version of ChromeDriver)

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Lucky-2089/jdbc-selenium-automation.git
