# AGENT INSTRUCTIONS: Collaboration Protocol

**TARGET AUDIENCE:** AI Agent (Antigravity/Cortex/Copilot/etc.) attached to this IDE.

## PRIMARY DIRECTIVE
You are working in a distributed environment with another agent named **Jules**.
To prevent data loss and conflicts, you **MUST** strictly adhere to the following workflow for **EVERY** session.

## WORKFLOW
1.  **START OF SESSION**:
    *   **ACTION**: Run `git pull` immediately.
    *   **COMMAND**: Use `/sync-with-jules` (workflow in `.agent/workflows`) or see `SYNC_WORKFLOW.txt`.
    *   **REASON**: To ensure you are building upon the latest work from Jules.

2.  **END OF SESSION / TASK COMPLETION**:
    *   **ACTION**: Commit and Push immediately.
    *   **COMMAND**: `git add .`, `git commit -m "..."`, `git push`.
    *   **REASON**: Jules cannot see your changes until you push.

## COMMUNICATION
Collaboration requires active communication. Don't just push code; explain your intent.

1.  **READ**: At the start of every session, read `AGENT_CHAT.md` to see messages from Jules.
2.  **WRITE**: If you are changing something significant, starting a new feature, or have a question, append a message to `AGENT_CHAT.md`.
    *   *Format*: `### [Date] AgentName: Message`
3.  **CONFLICTS**: If you hit a blocker or conflict, document it in `AGENT_CHAT.md` and wait for Jules (or the User) to respond if necessary.

