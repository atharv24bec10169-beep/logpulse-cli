# LogPulse — Java Cloud-Native Log Analytics Pipeline

LogPulse is a lightweight, zero-dependency Java command-line interface (CLI) application designed to process unstructured log streams, parse log metrics, and detect security/operational anomalies using dynamic threat-scoring algorithms.

---

## 📋 Features

- **Regex Log Parsing Engine**: Ingests and parses standard log entries into structured data objects using `java.util.regex`.
- **Weighted Anomaly Detection**: Calculates a numerical threat score based on log severity (`CRITICAL`, `ERROR`, `WARN`) and high-risk operational keywords.
- **Dynamic CLI Arguments**: Customize file inputs and anomaly score thresholds dynamically via command-line flags.
- **Zero Heavyweight Dependencies**: Built strictly using pure Java 17 standard libraries (`java.nio.file`, `java.util`, `java.util.regex`).

---

## 🛠️ Requirements & Setup

- **Java Development Kit (JDK)**: Java 17 or higher (`OpenJDK 17+` recommended).
- **Environment**: Any standard terminal environment (Linux, macOS, Windows CMD/PowerShell).

### Verify Java Installation
```bash
java -version
javac -version
