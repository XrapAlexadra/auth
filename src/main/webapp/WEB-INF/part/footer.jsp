<footer class="footer">
    <div class="container">
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>En</option>
            <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Ru</option>
        </select>
    </form>
</div>
</footer>

