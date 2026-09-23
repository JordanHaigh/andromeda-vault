# Self-Hosting

**Collection:** [[Topics/Collections/Command Line and Operations]]  
**Original section:** [Command Line and Operations source section](<../Resources/cheat.sh/Cheat.sh Guide.md>) — [jump to section](<../Resources/cheat.sh/Cheat.sh Guide.md#self-hosting>)

## Related topics

- [[Topics/Collections/command-line-search|Search]]
- [[Topics/Collections/command-line-special-pages|Special Pages]]

## Source content

## Self-Hosting

### Docker

Currently, the easiest way to get a self-hosted instance running is by using
the `docker-compose.yml` file.

    docker-compose up

This builds and runs the image with baked in cheatsheets and starts the app
and a Redis instance to back it, making the service available at
http://localhost:8002 This is currently an early implementation and should
probably not be used for anything outside of internal/dev/personal use right
now.
