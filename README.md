# 📱 Virus Transmission Simulation

As part of a university project, I developed a Java-based simulation modeling virus transmission between mobile phones based on proximity and concurrency.

Each cellphone is represented by an independent thread, simulating real-time behavior. Infected phones have a limited time to recover before either healing or being removed ("dying") from the simulation. Once three or more phones are infected, one is randomly selected to visit a virtual repair shop — adding an additional layer of system dynamics.

---

## 🧪 Features

- ✅ Each phone operates as its own thread (concurrent simulation)
- 🦠 Infection spreads with time-based recovery or failure
- 🔧 Automatic "repair shop" visits after multiple infections
- ⌨️ Real-time keyboard interaction

---

## 🎮 Controls

- **↑ (Up Arrow)** — Add a healthy phone to the simulation  
- **V** — Infect a random phone

---

## 💡 Highlights

This project demonstrates:

- 🧵 Proficiency in **multithreaded programming** (Java Threads)
- 🧠 Simulation of real-world dynamics in a digital context
- 🛠️ Event-driven design using keyboard input
- 🔄 Modeling of system behavior under viral spread scenarios

---

## 🚀 Getting Started

1. Clone or download the repository.
2. Navigate to the `dist/` folder.
3. Run the `.jar` file:
   ```bash
   java -jar Mobile_Virus_Simulation.jar

## 📁 Folder Structure

📁 dist/
└── Mobile_Virus_Simulation.jar

<hr>
<img width="737" alt="1" src="https://github.com/Chidalgo007/phone-virus/assets/145306497/b9fc1b35-6c77-4341-be42-45c1a0b9f283">
