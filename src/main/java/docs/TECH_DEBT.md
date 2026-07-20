

# DeployHub Technical Debt

## ✅ Completed

* JWT Authentication
* Git Repository Cloning
* Project Type Detection
* Strategy Pattern for Builds
* Automatic Build Pipeline
* ProjectRootResolver
* ProjectPathService

---

## 🚀 Planned Improvements

### 1. Parse package.json using Jackson

**Current**

```java
content.contains("\"build\"")
```

**Future**

```java
ObjectMapper
↓

JsonNode

↓

scripts.build
```

**Reason**

Avoid false positives and validate JSON structurally.

Priority: ⭐⭐⭐⭐☆

---

### 2. Project Validator Strategy

Instead of

```java
ProjectRootResolver
```

knowing React validation.

Introduce

```text
ProjectValidator
        │
        ├── ReactProjectValidator
        ├── SpringProjectValidator
        ├── PythonProjectValidator
        └── GradleProjectValidator
```

Priority: ⭐⭐⭐⭐⭐

---

### 3. Support More Project Types

Implement validators/builders for

* Spring Boot (`pom.xml`)
* Gradle (`build.gradle`)
* Python (`pyproject.toml`)
* Next.js
* Angular

Priority: ⭐⭐⭐⭐☆

---

### 4. Monorepo Support

Instead of

```text
findFirst()
```

allow

```text
frontend/
backend/
admin/
```

User chooses deployment root.

Priority: ⭐⭐⭐⭐⭐

---

### 5. Root Directory Configuration

Like Vercel.

Example

```text
Root Directory

frontend/
```

instead of assuming repository root.

Priority: ⭐⭐⭐⭐⭐

---

### 6. Cross-platform Command Execution

Current

```text
Windows

npm.cmd
```

Future

```text
Windows → npm.cmd

Linux → npm
```

Priority: ⭐⭐⭐⭐⭐

---

### 7. Better Exception Hierarchy

Replace

```java
RuntimeException
```

with

```text
ProjectNotFoundException

BuildFailedException

ProjectRootNotFoundException

UnsupportedProjectException
```

Priority: ⭐⭐⭐⭐☆

---

### 8. Deployment Logs

Persist

* clone logs
* build logs
* docker logs

Priority: ⭐⭐⭐⭐⭐

---

### 9. Docker

* Dockerfile generation
* docker build
* docker run

Priority: ⭐⭐⭐⭐⭐

---

### 10. Port Allocation

Automatically assign

```text
3001

3002

3003
```

Priority: ⭐⭐⭐⭐⭐

---

### 11. Deployment Status Machine

```text
CREATED

↓

CLONING

↓

BUILDING

↓

BUILT

↓

DEPLOYING

↓

RUNNING

↓

FAILED
```

Priority: ⭐⭐⭐⭐⭐

---

# Why this is valuable

Every time we postpone something, we add it here.

So instead of thinking:

> "I'll remember it later."

You think:

> "It's documented. We can improve it when it becomes the highest priority."

That's exactly how software teams work.

---

## One more thing I'd start now

I'd also keep an **Architecture Decisions** file:

```text
docs/
├── ROADMAP.md
├── TECH_DEBT.md
└── ADR/
    ├── 001-use-strategy-pattern.md
    ├── 002-project-root-resolver.md
    └── 003-command-executor.md
```

